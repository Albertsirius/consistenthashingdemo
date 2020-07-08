package hzhsirius.homework.consistenthashing;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author AlbertSirius
 * @createtime 2020/7/7
 * @since 1.0.0
 */
public class ConsistentHashDemo {

    private static final String[] nodesIP = new String[] {
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.3",
            "192.168.0.4",
            "192.168.0.5",
            "192.168.0.6",
            "192.168.0.7",
            "192.168.0.8",
            "192.168.0.9",
            "192.168.0.10"
    };

    private static final long requestCount = 1000000L;


    public static void main(String[] args) {

        List<Node> nodes = Stream.of(nodesIP).map(s -> new Node(s)).collect(Collectors.toList());

        ConsistentHash consistentHash = new ConsistentHash(1000, nodes);

        for(long i = 0; i < requestCount; i++){
            consistentHash.getNode(UUID.randomUUID().toString()).addOne();
        }


        long average = requestCount / nodesIP.length;

        double sum = 0;
        for(Node node : nodes){
            System.out.println(node.getIpAdress() + ": " + node.getCountNum());
            sum += (average - node.getCountNum()) * (average - node.getCountNum());
        }
        double standardDev = Math.sqrt(sum / (nodes.size()));
        System.out.println(standardDev);

    }
}
