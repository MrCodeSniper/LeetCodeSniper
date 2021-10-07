package mo.gov.safp.portal.softtoken.event;

public class IdentityEvent {
    int code;
    Object data;

    public int getCode() {
        return code;
    }

    public IdentityEvent(int code) {
        this.code = code;
    }

    public IdentityEvent(int code,Object data) {
        this.code = code;
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
