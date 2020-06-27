/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolcf;

import java.io.IOException;
import java.util.Map;


import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 *
 * @author KV
 */
public class test {
    private String csrf;
    private Map<String, String> cookies = null;
    private String handle;
    
    public String getHandle() {
        return this.handle;
    }
    
    /**
     * Get Cookies and CSRF Token in order to submit the code
     * @param url The path to get CSRF token
     * @return Boolean value: false when exception occurs
     */
    public boolean getCookiesAndCsrf(String url) {
        try {
            // Get request to acquire csrf_token and cookies
            // Cookies will be null if user not yet logged in
            Connection.Response form;
            if (cookies == null) {
                form = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .execute();
            } else {   
                form = Jsoup.connect(url)
                        .cookies(cookies)
                        .method(Connection.Method.GET)
                        .execute();
            }
            
            String csrf_token = Jsoup.parse(form.body()).select("input[name=\"csrf_token\"]").first().attr("value");
            System.out.println(csrf_token);
            
            // Set cookies and csrf for later use
            this.csrf = csrf_token;
            this.cookies = form.cookies();
            
            return true;
        } catch (IOException ex) {
//            ex.printStackTrace();
            System.out.println("Unexpected error:\n" + ex.toString());
            return false;
        }
    }
    
    /**
     * Attempt to login to https://codeforces.com/enter
     * @param handle Handle or email
     * @param password Login password
     * @return Boolean value: true if the user successfully logged in, false otherwise
     */
    public boolean login(String handle, String password) {
        try {
            getCookiesAndCsrf("https://codeforces.com/enter");
            // Login with acquired csrf_token and cookies
            Connection.Response loginRes = Jsoup.connect("https://codeforces.com/enter")
                    .data("action", "enter")
                    .data("handleOrEmail", handle)
                    .data("password", password)
                    .data("remember", "true")
                    .data("csrf_token", this.csrf)
                    .cookies(this.cookies)
                    .method(Connection.Method.POST)
                    .execute();
            
            // Set handle (username)
            this.handle = handle;
            this.cookies = loginRes.cookies();
            
            // Get all the link with href attr equals to /profile/<handle>
            // if es is empty, login failed (if it was succeeded, there always a link to /profile/<handle>)
            Document resDoc = Jsoup.parse(loginRes.body());  // parse to Document to search
            Elements es = resDoc.getElementsByAttributeValue("href", "/profile/" + handle);
            
            if (!es.isEmpty()) {
                System.out.println("Welcome " + this.handle);
                return true;
            } else {
                System.out.println("Wrong handle/email or password");
                return false;
            }
        } catch (IOException ex) {
//            ex.printStackTrace();
            System.out.println("Unexpected error:\n" + ex.toString());
            return false;
        }
    }
    
    /**
     * Attempt to submit the source code with GNU G++ 11 compiler
     * @param contestId ID of the contest
     * @param problemId ID of the problem (A, B, C, D, F1, F2, etc.)
     * @param filePath (Path to the source code file)
     * @return Boolean value: true if the submission could get to the server, false otherwise
     */
    public boolean submit(String contestId, String problemId, String source) {
        getCookiesAndCsrf("https://codeforces.com/problemset/submit");
        
        String actionUrl = "https://codeforces.com/problemset/submit?csrf_token=" + this.csrf;
        
        try {
            Connection.Response submitRes = Jsoup.connect(actionUrl)
                    .data("csrf_token", this.csrf)
                    .data("action", "submitSolutionFormSubmitted")
                    .data("contestId", "4")
                    .data("submittedProblemIndex", "A")
//                    .data("submittedProblemCode", contestId + problemId)
                    .data("programTypeId", "42")
                    .data("source", source)
                    .data("tabSize", "4")
                    .data("sourceCodeConfirmed", "true")
                    .method(Connection.Method.POST)
                    .cookies(this.cookies)
                    .followRedirects(true)
                    .execute();
            
            // Get all the link with href attr equals to /profile/<handle>
            // if es is empty, login failed (if it was succeeded, there always a link to /profile/<handle>)
            Document resDoc = Jsoup.parse(submitRes.body());  // parse to Document to search
            Elements es = resDoc.getElementsByAttributeValue("href", "/profile/" + handle);
            
            if (!es.isEmpty()) {
                System.out.println("Submitted");
                return true;
            } else {
                System.out.println("Submitted failed");
                return false;
            }
        } catch (IOException ex) {
//            ex.printStackTrace();
            System.out.println("Unexpected error:" + ex.toString());
            return false;
        }
    }
    
    public String getLastestSubmissionVerdict() {
        try {
            Connection.Response res = Jsoup.connect("https://codeforces.com/api/user.status?handle=koikoi&from=1&count=1")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
            
            String body = res.body();
            String verdict = "\"verdict\":\"";
            int startPos = body.indexOf(verdict);
            int endPos = body.indexOf("\"", startPos + verdict.length());
            return body.substring(startPos + verdict.length(), endPos);
//            System.out.println(body.substring(startPos, 5));
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
    
    public static void main(String args[]) {

        test hi = new test();
        String source = "#include <iostream>\n" +
                        "\n\n" +
                        "using namespace std;\n" +
                        "\n" +
                        "int main()\n" +
                        "{\n" +
                        "    int n;\n" +
                        "    cin >> n;\n" +
                        "\n" +
                        "    if (n%2 == 0 && n != 2)\n" +
                        "        cout << \"YES\";\n" +
                        "    else\n" +
                        "        cout << \"NO\";\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}";
        
//        if (login("username", "password")) {
//            submit("4", "A", source);
//        }
        
        System.out.println(hi.getLastestSubmissionVerdict());
    }
}
