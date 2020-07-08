package hzhsirius.homework.consistenthashing;


/**
 * 采用FNV算法
 *
 * @author AlbertSirius
 * @createtime 2020/7/8
 * @since 1.0.0
 */
public class FNVHashAlgrth implements HashAlgrth {
    private final static int PRIME = 16777619;
    private final static long OFFSET_BASIC = 2166136261L;

    @Override
    public int getHashCode(String data) {
        int hashCode = (int) OFFSET_BASIC;
        for (int i = 0; i < data.length(); i++) {
            hashCode = (hashCode ^ data.charAt(i)) * PRIME;
        }
        hashCode += hashCode << 13;
        hashCode ^= hashCode >> 7;
        hashCode += hashCode << 3;
        hashCode ^= hashCode >> 17;
        hashCode += hashCode << 5;
        if (hashCode < 0) {
            hashCode = Math.abs(hashCode);
        }
        return hashCode;
    }
}
