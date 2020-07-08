package hzhsirius.homework.consistenthashing;

import java.util.*;

/**
 * TODO
 *
 * @author AlbertSirius
 * @createtime 2020/7/7
 * @since 1.0.0
 */
public class ConsistentHash {

    //虚拟节点数
    private int replicasCount;

    private HashAlgrth hashAlgrth = new FNVHashAlgrth();

    private SortedMap<Integer, Node> hashCircle = new TreeMap<> ();

    public ConsistentHash(int replicasCount, Collection<Node> nodes){
        this.replicasCount = replicasCount;
        nodes.forEach( n -> addNode(n));

    }

    public void addNewNode(Node node) {
        if(node == null)
            throw new IllegalArgumentException("Node must be not null");
        if(hashCircle.containsKey(hashAlgrth.getHashCode(node.createVirtualIP(0))))
            throw new IllegalArgumentException("Node does exist!");
        addNode(node);

    }

    private void addNode(Node node) {
        for(int i = 0; i < replicasCount; i++) {
            hashCircle.put(hashAlgrth.getHashCode(node.createVirtualIP(i)), node);
        }
    }

    public Node getNode(String keyData) {
        if(hashCircle.isEmpty())
            return null;
        int hashCode = hashAlgrth.getHashCode(keyData);
        if(!hashCircle.containsKey(hashCode)){
            SortedMap<Integer, Node> tailMap = hashCircle.tailMap(hashCode);
            hashCode = tailMap.isEmpty() ? hashCircle.firstKey() : tailMap.firstKey();
        }
        return hashCircle.get(hashCode);
    }


}
