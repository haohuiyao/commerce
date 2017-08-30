package com.meeno.framework.exception;


public class BusinessLogicException extends BusinessCheckException  {

    public BusinessLogicException(){
        super();
    }
    public BusinessLogicException(String string) {
        super(string);
    }

    public BusinessLogicException(String s, Throwable e){
        super(s,e);
    }
    public BusinessLogicException(Throwable e) {
        super(e);
    }
    public BusinessLogicException(String key, Object[] objectParam) {
        super(key,objectParam);
    }
    /**
     *
     */
    private static final long serialVersionUID = -5233772330040048466L;
}
