package com.tmdt.converter;

public interface IConverter<U,V> {
	V toDTO(U u);
	U toEntity(V v);
}
