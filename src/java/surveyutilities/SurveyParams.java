/*
 * This class provides the Survey items for the form as well as the key to the survey responses
 * populates the surveyForm.jsp
 */
package surveyutilities;

public class SurveyParams {
    
    private final String[] catCode = {"rtFriend", "rtSpace", "rtComfort", "rtClean", "rtNoise"};
    private final String[] catTxt = {"Friendliness of customer staff", "Space for luggage storage", 
        "Comfort of seating", "Cleanliness of aircraft", "Noise level of aircraft"};
    private final String[] rateKey = {"Neutral", "Poor", "Fair", "Good", "Excellent"};
    private final String[] reqField = {catCode[0], catCode[1], catCode[2], catCode[3], catCode[4], 
        "airlineInput", "flightNum", "flightDest"};
    private final String[] nonReqField = {"respFName", "respLName", "respEmail"};
    
    public SurveyParams() {
    }
    public String[] getCatCode() {
        return this.catCode;
    }
    public String[] getCatTxt() {
        return this.catTxt;
    }
    public String[] getRateKey() {
        return this.rateKey;
    }
    public String[] getReqField() {
        return this.reqField;
    }
    public String[] getNonReqField() {
        return this.nonReqField;
    }
    public String getCatCode(int num) {
        return (num > -1 && num < catCode.length) ? catCode[num] : catCode[0];
    }
    public String getCatTxt(int num) {
        return (num > -1 && num < catTxt.length) ? catTxt[num] : catTxt[0];
    }
    public String getrateKey(int num) {
        return (num > -1 && num < rateKey.length) ? rateKey[num] : rateKey[0];
    }
    public String getReqField(int num) {
        return reqField[num];
    }
    public String getNonReqField(int num) {
        return this.nonReqField[num];
    }
}
