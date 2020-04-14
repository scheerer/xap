

/**
 * Title:        XAP (XML / LDAP Integration)
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Ball State University CS498
 * @author John Iles, Russell Scheerer, Raood Talarico
 * @version 1.0
 */

public class Message
{
  public Message()
  {
    errorID = ErrorCode.VALID_EXECUTION ;
    errorMessage = "Valid Execution";
  }

  public Message(int id, String message)
  {
    errorID = id;
    errorMessage = message;
  }
  public int getErrorCode()
  {
    return errorID;
  }
  public String getErrorMessage()
  {
    return errorMessage;
  }
  private int errorID = 0;
  private String errorMessage;
}