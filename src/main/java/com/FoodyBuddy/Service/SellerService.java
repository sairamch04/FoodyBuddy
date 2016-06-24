package com.FoodyBuddy.Service;


public interface SellerService {
	public boolean AddSeller(String name, String email,String mobile, String flat_number , int apartmentId) throws Exception;
	public boolean DeleteSeller(Integer id) throws Exception;
}
