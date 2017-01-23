/*
 * These magic beans will grow into a mighty beanstalk! Used to store airline survey form information for session
 * 
 */
package surveyutilities;

public class SurveyBeans {
    
    private int rtFriend, rtSpace, rtComfort, rtClean, rtNoise;
    private String airlineInput, flightNum, flightDest, respHost, respFName, respLName, respEmail;
    private boolean[] nonReqInsert = {false, false, false};

    public SurveyBeans() {
        rtFriend = rtSpace = rtComfort = rtClean = rtNoise = -1;
    }
    public int getRtFriend() {
        return rtFriend;
    }
    public void setRtFriend(int rtFriend) {
        this.rtFriend = rtFriend;
    }
    public int getRtSpace() {
        return rtSpace;
    }
    public void setRtSpace(int rtSpace) {
        this.rtSpace = rtSpace;
    }
    public int getRtComfort() {
        return rtComfort;
    }
    public void setRtComfort(int rtComfort) {
        this.rtComfort = rtComfort;
    }
    public int getRtClean() {
        return rtClean;
    }
    public void setRtClean(int rtClean) {
        this.rtClean = rtClean;
    }
    public int getRtNoise() {
        return rtNoise;
    }
    public void setRtNoise(int rtNoise) {
        this.rtNoise = rtNoise;
    }
    public String getAirlineInput() {
        return airlineInput;
    }
    public void setAirlineInput(String airlineInput) {
        this.airlineInput = airlineInput;
    }
    public String getFlightNum() {
        return flightNum;
    }
    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }
    public String getFlightDest() {
        return flightDest;
    }
    public void setFlightDest(String flightDest) {
        this.flightDest = flightDest;
    }
    public String getRespHost() {
        return respHost;
    }
    public void setRespHost(String respHost) {
        this.respHost = respHost;
    }
    public String getRespFName() {
        return respFName;
    }
    public void setRespFName(String respFName) {
        this.respFName = respFName;
    }
    public String getRespLName() {
        return respLName;
    }
    public void setRespLName(String respLName) {
        this.respLName = respLName;
    }
    public String getRespEmail() {
        return respEmail;
    }
    public void setRespEmail(String respEmail) {
        this.respEmail = respEmail;
    }
    public boolean getNonReqInsert(int num) {
        return this.nonReqInsert[num];
    }
    public void setNonReqInsert(int num, boolean nonReqInsert) {
        this.nonReqInsert[num] = nonReqInsert;
    }   
}
