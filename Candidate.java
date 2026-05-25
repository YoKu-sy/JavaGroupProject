public class Candidate implements Comparable<Candidate> {

    private final String locationId;
    private final int priorityScore;

    public Candidate(String locationId, int priorityScore) {
        this.locationId = locationId;
        this.priorityScore = priorityScore;
    }

    public String getLocationId() {
        return locationId;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    @Override
    public int compareTo(Candidate other) {
        // sort by score descending, then id ascending if tied
        int scoreComparison = Integer.compare(other.priorityScore, this.priorityScore);
        if (scoreComparison != 0) {
            return scoreComparison;
        }
        return this.locationId.compareTo(other.locationId);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + locationId + '\'' +
                ", score=" + priorityScore +
                '}';
    }
}
