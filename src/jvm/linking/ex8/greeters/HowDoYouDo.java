package jvm.linking.ex8.greeters;

import jvm.linking.ex8.com.artima.greeter.Greeter;
public class HowDoYouDo implements Greeter {

    public void greet() {
        System.out.println("How do you do, globe!");
    }
}
