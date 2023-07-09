package estore;

public class products {

    private Integer prNO;
    private String prName;
    private String prAddress;
    private String prpic;
    private String prData;
    private Double prPrice;
    private Double prcurrency;
    private Double prQuantety;
    private Double prTotal;
    private Integer userId;

    public products() {
    }

    public products(Integer prNO, String prName, String prAddress, String prpic, String prData, Double prPrice, Double prcurrency, Double prQuantety, Double prTotal, Integer userId) {
        this.prNO = prNO;
        this.prName = prName;
        this.prAddress = prAddress;
        this.prpic = prpic;
        this.prData = prData;
        this.prPrice = prPrice;
        this.prcurrency = prcurrency;
        this.prQuantety = prQuantety;
        this.prTotal = prTotal;
        this.userId = userId;
    }

    public Integer getPrNO() {
        return prNO;
    }

    public void setPrNO(Integer prNO) {
        this.prNO = prNO;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getPrAddress() {
        return prAddress;
    }

    public void setPrAddress(String prAddress) {
        this.prAddress = prAddress;
    }

    public String getPrpic() {
        return prpic;
    }

    public void setPrpic(String prpic) {
        this.prpic = prpic;
    }

    public String getPrData() {
        return prData;
    }

    public void setPrData(String prData) {
        this.prData = prData;
    }

    public Double getPrPrice() {
        return prPrice;
    }

    public void setPrPrice(Double prPrice) {
        this.prPrice = prPrice;
    }

    public Double getPrcurrency() {
        return prcurrency;
    }

    public void setPrcurrency(Double prcurrency) {
        this.prcurrency = prcurrency;
    }

    public Double getPrQuantety() {
        return prQuantety;
    }

    public void setPrQuantety(Double prQuantety) {
        this.prQuantety = prQuantety;
    }

    public Double getPrTotal() {
        return prPrice * prQuantety;
    }

    public void setPrTotal(Double prTotal) {
        this.prTotal = prTotal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "products{" + "prNO=" + prNO + ", prName=" + prName + ", prAddress=" + prAddress + ", prpic=" + prpic + ", prData=" + prData + ", prPrice=" + prPrice + ", prcurrency=" + prcurrency + ", prQuantety=" + prQuantety + ", prTotal=" + prTotal + ", userId=" + userId + '}';
    }

}
