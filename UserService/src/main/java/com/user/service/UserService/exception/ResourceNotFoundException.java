package com.user.service.UserService.exception;



public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException()
	{
		super("Resource not found !!!!!");
	}
}


//public class ResourceNotFoundException extends RuntimeException {
//	
//	public ResourceNotFoundException()
//	{
//		super("Resourcenot  found  on server  !!");
//	}
//	
//	public ResourceNotFoundException(String message)
//	{
//		super(message);
//	}
//
//}
