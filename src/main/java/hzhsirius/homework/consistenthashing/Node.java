package hzhsirius.homework.consistenthashing;

/**
 * <p>服务器节点</p>
 * @author AlbertSirius
 * @createtime 2020/7/7
 * @since 1.0.0
 */
public class Node {

    private String IpAdress;

    //用于记录当前服务器记录的对象数
    private int countNum = 0;

    public String getIpAdress() {
        return IpAdress;
    }

    public void setIpAdress(String ipAdress) {
        IpAdress = ipAdress;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public void addOne() {
        this.countNum += 1;
    }

    //虚拟IP形式为"XXX.XXX.XXX.XXX_INT"
    public String createVirtualIP(int replicaNumber) {
        return IpAdress + "_" + Integer.toString(replicaNumber);
    }

    public Node(String ipAdress) {
        IpAdress = ipAdress;
    }
}
