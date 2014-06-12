/**
 * Data class for storing questions.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class Question
{
  /**
   * Array for holding Strings that create the question.
   */
 private String [] question =  new String [6];
 /**
   * Array for holding Strings that create the options.
   */
 private String [] options = new String [4];
 /**
   * The answer to the question.
   */
 private String answer;
 
 /**
   * Empty constructor of a question.
   */
 public Question ()
 {
   
 }
 
 /**
   * Constructor of a question intializing all the data fields.
   * 
   * @param String[] question the question
   * @param String[] options the question options
   * @param String answer the answer to the question
   */
 public Question (String [] question, String [] options, String answer)
 {
   this.question = question;
   this.options = options;
   this.answer = answer;
}
 
 /**
   * Sets a line of the question
   * 
   * @param int index the location to put the new line
   * @param String questionLine the line to be added.
   */
 public void setQuestion (int index,String questionLine)
 {
   question [index] = questionLine;
 }
 
  
 /**
   * Sets a line of the options
   * 
   * @param int index the location to put the new line
   * @param String option the line to be added.
   */
 public void setOption (int index, String option)
 {
   options [index] = option;
 }
 
 /**
  * Sets the answer to the question
  * 
  * @param String answer the answer to the question
  */
 public void setAnswer (String answer)
 {
   this.answer = answer;
 }
 
 /**
  * Accessor method to access the question.
  * 
  * @return an array of Strings that make up the question.
  */
 public String[] getQuestion ()
 {
   return question;
 }
 
  /**
  * Accessor method to access the options.
  * 
  * @return an array of Strings that make up the options.
  */
 public String[] getOptions ()
 {
   return options;
 }
 
  /**
  * Accessor method to access the answer.
  * 
  * @return a String that is the answer to the question.
  */
 public String getAnswer ()
 {
  return answer; 
 }
}
 