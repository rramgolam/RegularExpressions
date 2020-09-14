package com.company.regularexpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String string = "I am a string. Yes, I am.";
        System.out.println(string);
        // simple string replacement
        String yourString = string.replaceAll("I", "You");
        System.out.println(yourString);

        // replace everything with Y
        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(alphanumeric.replaceAll(".","Y"));

        // replace 'abcDeee' only at the start with Y
        System.out.println(alphanumeric.replaceAll("^abcDeee", "YYY"));

        // replace 'abcDeee' only at the start with Y - second occ. missed
        String secondString = "abcDeeeF12GhhabcDeeeiiiijkl99z";
        System.out.println(secondString.replaceAll("^abcDeee", "YYY"));

        // .matches takes the whole string not part
        System.out.println(alphanumeric.matches("^hello"));
        System.out.println(alphanumeric.matches("^abcDeee"));
        System.out.println(alphanumeric.matches("abcDeeeF12Ghhiiiijkl99z"));

        // matches ijkl99z characters at the end
        System.out.println(alphanumeric.replaceAll("ijkl99z$", "THE END"));

        // matches a or e or i
        System.out.println(alphanumeric.replaceAll("[aei]", "X"));
        // matches a or e or i to replace with a string
        System.out.println(alphanumeric.replaceAll("[aei]", "I replaced a letter here"));
        // matches two sets of characters next to each other e.g. aj and replaces with X
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));

        // first two chars in a string H or h to be (uppercase it)
        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));


        String newAlphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        // doesn't include r or j
        System.out.println(newAlphanumeric.replaceAll("[^ej]", "X"));
        // doesn't include a,b,c,d,e,f,3,4,5,6,7,8
        System.out.println(newAlphanumeric.replaceAll("[abcdef345678]", "X"));
        // accept a through f or A through F and between 3 and 8
        System.out.println(newAlphanumeric.replaceAll("[a-fA-F3-8]", "X"));
        // Case Insensitive flag (?i) - upper case or lowercase matches
        System.out.println(newAlphanumeric.replaceAll("(?i)[a-f3-8]", "X"));
        // matches 0 to 9
        System.out.println(newAlphanumeric.replaceAll("[0-9]", "X"));
        // gets all digits
        System.out.println(newAlphanumeric.replaceAll("\\d", "X"));
        // gets all non-digits
        System.out.println(newAlphanumeric.replaceAll("\\D", "X"));


        String hasWhitespace = "I have blanks and\ta tab, and also a newline\n";
        System.out.println(hasWhitespace);
        // remove all whitespace - including tabs  and new line
        System.out.println(hasWhitespace.replaceAll("\\s", ""));

        // can match string operations too (\n \t etc..)
        System.out.println(hasWhitespace.replaceAll("\t", "X"));

        // to get all Non-whitespace
        System.out.println(hasWhitespace.replaceAll("\\S", ""));
        // to match all lowercase a-z 0-9 and _
        System.out.println(newAlphanumeric.replaceAll("\\w", "X"));
        System.out.println(hasWhitespace.replaceAll("\\w", "X"));
        // to surround words --> XwordX XanotherX - useful for creating html or any tags
        System.out.println(hasWhitespace.replaceAll("\\b", "X"));


        String thirdAlphanumericString = "abcDeeeF12Ghhiiiijkl99z";
        // starts with abcD and needs 3 e's
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe{3}", "YYY"));
        // starts with abcD and has one or more e's
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe+", "YYY"));
        // starts with abcD and has 0 or more e's
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe*", "YYY"));
        // starts with abcD and has between 2 to 5 e's
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe{2,5}", "YYY"));
        // has a h followed by one or more i's and maybe a j
        System.out.println(thirdAlphanumericString.replaceAll("h+i*j", "Y"));

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");

        //String h2Pattern = ".*<h2>.*"; to get everything from System.out.println(matcher.matches());
        // won't work for occurrences search
        String h2Pattern = "<h2>";
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        // needs to reset after .matches() - state is at the end
        matcher.reset();
        int count = 0;
        while(matcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());

        }

        // different way using  groups
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());

        groupMatcher.reset();

        while(groupMatcher.find()) {
            // .group(0) is the entire text, 1 is the first group
            System.out.println("Occurrence: " + groupMatcher.group(1));
        }

        // this time using 3 groups - 2 for tags one for content within tags
        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPatten = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPatten.matcher(htmlText);

        while(h2TextMatcher.find()) {
            // gets content
            System.out.println("Occurrence: " + h2TextMatcher.group(2));
        }

        // "abc" "a" and "b" and "c"  - already uses logical operator AND
        // [[Hh]arry    - uses OR
        // can explicitly state op
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));

        // [^abc]
        String tvTest = "tstvtkt";
//        String tNotVRegExp = "t[^v]"; // always assumes a character after t
        String tNotVRegExp = "t(?!v)";  // Negative Look Ahead - still won't include extra v in .start and .end
                                        // use Positive Look Ahead for that t(?=v)
        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        count = 0;
        while(tNotVMatcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
        }

        // regex to validate American phone number
        // ^([\(]{1}[0-9]{3}[\)]{1}[ ]{1}[0-9]{3}[\-]{1}[0-9]{4})$
        String phone1 = "1234567890";  // Shouldn't match
        String phone2 = "(123) 456-7890"; // match
        String phone3 = "123 456-7890"; // Shouldn't match
        String phone4 = "(123)456-7890"; // Shouldn't match

        System.out.println("phone1 = " + phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone2 = " + phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone3 = " + phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone4 = " + phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));

        // regex to validate visa card number
        // i.e. starts with a 4, has 12 digits from 0 to 9, followed possible by up to 3 more digits between 0 and 9
        // ^4[0-9]{12}([0-9]{3})?$
        String visa1 = "4444444444444"; // should match
        String visa2 = "5444444444444"; // shouldn't match
        String visa3 = "4444444444444444";  // should match
        String visa4 = "4444";  // shouldn't match

        System.out.println("visa1 " + visa1.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa2 " + visa2.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa3 " + visa3.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa4 " + visa4.matches("^4[0-9]{12}([0-9]{3})?$"));


    }
}
