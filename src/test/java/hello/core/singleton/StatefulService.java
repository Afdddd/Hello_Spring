package hello.core.singleton;

public class StatefulService {


   // private int price;

    //지역변수로 리턴값을받아 따로 저장한다.
    public int order(String name, int price) {
        System.out.println("name = " + name +" price = "+price);
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
