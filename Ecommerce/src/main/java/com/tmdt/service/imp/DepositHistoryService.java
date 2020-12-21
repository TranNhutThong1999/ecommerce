package com.tmdt.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.entity.DepositHistory;
import com.tmdt.entity.State;
import com.tmdt.repository.DepositHistoryRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.security.CustomUserDetail;
import com.tmdt.service.IDepositHistoryService;
import com.tmdt.util.ConvertMoneyToXu;

@Service
public class DepositHistoryService implements IDepositHistoryService{
	@Autowired
	private DepositHistoryRepository depositHistoryRepository;
	
	@Autowired
	private CustomUserDetail customUserDetail;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConvertMoneyToXu convertMoneyToXu;
	
	@Override
	public void save(int money,State s) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			DepositHistory d = new DepositHistory();
			d.setMoney(money);
			d.setUser(userRepository.findOneByUserName(customUserDetail.getPrinciple().getName()).get());
			d.setState(s);
			d.setTime(new Date());
			d.setType("Paypal");
			d.setXu(convertMoneyToXu.handle(money));
			depositHistoryRepository.save(d);
	}

}
