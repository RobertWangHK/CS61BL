import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserExercises extends DBTable<User> {
    UserExercises() {
    }

    UserExercises(Collection<User> lst) {
        super(lst);
    }

    /**
     * Get an ordered List of Users, sorted first on age,
     * then on their id if the age is the same.
     */
    public List<User> getOrderedByAgeThenId() {
        return getEntries().stream().sorted((a,b)->{
            if(a.getAge()!=b.getAge()){
                return a.getAge()-b.getAge();
            }
            else{
                return a.getId()-b.getId();
            }
        }).collect(Collectors.toList());
        //return null; // FIX ME
    }

    /**
     * Get the average age of all the users.
     * If there are no users, the average is 0.
     */
    public double getAverageAge() {
        double sum = getEntries().stream().filter(s->s.getAge()!=0).mapToDouble(s->s.getAge()).sum();
        long num = getEntries().stream().filter(s->s.getAge()!=0).count();
        if(num==0){
            return 0;
        }
        else{
            return sum/num;
        }
        //return -1; // FIX ME
    }
    /**
     * Group usernames by user age, for all users that have an age greater than min_age.
     * Usernames with ages less than or equal to min_age are excluded.
     * Returns a Map from each age present to a list of the usernames that have that age.
     */
    public Map<Integer, List<String>> groupUsernamesByAgeOlderThan(int min_age) {
        return getEntries().stream().filter(s->s.getAge()>min_age)
.collect(Collectors.groupingBy(s->s.getAge(),Collectors.mapping(s->s.getUsername(),Collectors.toList())));//
        //return null;
    }
}
