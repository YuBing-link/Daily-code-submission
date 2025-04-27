package Smart_home_appliance_control_system;

public class home_app implements press {
    private String name;
    private boolean status;

    public home_app() {}
    public home_app(String name, boolean status){
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void press() {
        status = !status;
    }
}
