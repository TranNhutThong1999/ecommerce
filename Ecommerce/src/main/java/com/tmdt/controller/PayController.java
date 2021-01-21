package com.tmdt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import com.tmdt.entity.DepositHistory;
import com.tmdt.entity.State;
import com.tmdt.paypal.PaypalPaymentIntent;
import com.tmdt.paypal.PaypalPaymentMethod;
import com.tmdt.paypal.Utils;
import com.tmdt.service.IDepositHistoryService;
import com.tmdt.service.IUserService;
import com.tmdt.service.imp.PaypalService;
import com.tmdt.util.MessagesUtil;

@Controller
public class PayController {

	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";

	@Autowired
	private PaypalService paypalService;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private MessagesUtil messageUtil;
	
	@Autowired
	private IDepositHistoryService depositHistoryService;
	
	@GetMapping("/payment")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN') ")
	public String index(@RequestParam(required = false, defaultValue = "") String message, ModelMap modelMap) {
		if (message.equals("pay_success")) {
			modelMap.addAttribute("message","pay_success");
		}
		return "Payment";
	}

	@PostMapping("/pay")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN') ")
	public String pay(HttpServletRequest request, @RequestParam("price") double price) {
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
		}
		return "redirect:/";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN') ")
	public String cancelPay() {
		return "redirect:/payment?message=pay_error";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN') ")
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				int total = 0;
				for (Transaction e : payment.getTransactions()) {
					Double a =Double.valueOf(e.getAmount().getTotal());
					total += a.intValue();
				}
				depositHistoryService.save(total,State.SUCCESS);
				userService.setTotalMoney(total);
				return "redirect:/payment?message=pay_success";
			}
		} catch (PayPalRESTException e) {
		}
		return "redirect:/";
	}
}
