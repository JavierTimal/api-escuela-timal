package com.timal.app.escuela.services;


import com.timal.app.escuela.dtos.ObjResponse;

public interface IService<T, S> {
	public T create(S obj);
	
	public ObjResponse getAll(int numeroDePagina,int medidaDePagina,String ordenarPor,String sortDir);
	
	public T getById(long id);
	
	public T update(S obj, long id);
	
	public void delete(long id);
}
