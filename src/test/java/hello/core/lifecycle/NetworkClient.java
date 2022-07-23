package hello.core.lifecycle;

public class NetworkClient {
    private String url;

    // 의존관계가 주입되기 전에 초기화를 했기 때문에 url은 null이다.
    public NetworkClient() {
        System.out.println("생성자 호출, url = "+url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void connect() {
        System.out.println("connect: "+url);
    }

    public void call(String message) {
        System.out.println("call: "+url+" message: "+message);
    }

    public void disconnect(){
        System.out.println("close "+url);
    }
}
