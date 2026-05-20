public class Candidate implements Comparable<Candidate> {
    
    // 1. 私有属性 (封装)
    private String locationId;
    private double priorityScore;

    // 2. 构造函数
    public Candidate(String locationId, double priorityScore) {
        this.locationId = locationId;
        this.priorityScore = priorityScore;
    }

    // 3. Getters (通常实体类在排序时只需要读，不需要改，所以只写Getter也可以)
    public String getLocationId() {
        return locationId;
    }

    public double getPriorityScore() {
        return priorityScore;
    }

    // 4. 重写 compareTo 方法 (定义排序规则)
    @Override
    public int compareTo(Candidate other) {
        // 第一步：比较 priority_score (降序)
        // 注意：Double.compare(d1, d2) 默认是升序。
        // 为了实现降序，我们将 other 的分数放在前面，当前对象 this 的分数放在后面。
        int scoreComparison = Double.compare(other.priorityScore, this.priorityScore);
        
        // 如果分数不相等，直接返回分数的比较结果
        if (scoreComparison != 0) {
            return scoreComparison;
        }
        
        // 第二步：如果分数相等，比较 location_id (升序)
        // 字符串的 compareTo 默认就是升序，所以 this 在前，other 在后
        return this.locationId.compareTo(other.locationId);
    }
    
    // 5. (可选) 重写 toString 方法，方便后续打印测试结果
    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + locationId + '\'' +
                ", score=" + priorityScore +
                '}';
    }
}