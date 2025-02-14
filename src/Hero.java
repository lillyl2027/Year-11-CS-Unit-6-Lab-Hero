public class Hero {
    private String name;
    private int hitPoints;

    public Hero(String name){
        this.name = name;
        this.hitPoints = 100;
    }

    public String getName(){
        return name;
    }

    public int getHitPoints(){
        return hitPoints;
    }

    public String toString(){
        return "Hero{name='" + name + "', " + "hitPoints=" + hitPoints + "}";
    }

    public void attack(Hero opponent){
        double random = Math.random();
        if (random < 0.5) {
            this.hitPoints = hitPoints + 10;
        }
        else if (random >= 0.5) {
            opponent.hitPoints = hitPoints - 10;
        }
    }

    public void senzuBean(){
        this.hitPoints = 100;
    }

    private void fightUntilTheDeathHelper(Hero opponent) {
        while (true) {
            if (this.hitPoints > 0 && opponent.getHitPoints() > 0) {
                this.attack(opponent);
            } else {
                break;
            }

            if (this.hitPoints > 0 && opponent.getHitPoints() > 0) {
                opponent.attack(this);
            } else {
                break;
            }
        }
    }

    public String fightUntilTheDeath(Hero opponent){
        this.senzuBean();
        opponent.senzuBean();
        fightUntilTheDeathHelper(opponent);
        return this.name + "= " + this.hitPoints + "   " + opponent.name + "= " + opponent.hitPoints;
    }

    private int[] nFightsToTheDeathHelper(Hero opponent, int n){
        int[] fightsWon = new int[2];
        for (int i = 0; i<n; i++){
            fightUntilTheDeathHelper(opponent);
            if (hitPoints>0){
                fightsWon[0]++;
            }
            else {
                fightsWon[1]++;
            }

        }
        return fightsWon;
    }

    public String nFightsToTheDeath(Hero opponent, int n) {
        int[] results = nFightsToTheDeathHelper(opponent, n); // can't call directly bc quiet

        String returnMessage;
        if (results[0] == results[1]) {
            returnMessage = "OMG! It was actually a draw!";
        } else if (results[0] > results[1]) {
            returnMessage = this.name + " wins!";
        } else {
            returnMessage = opponent.getName() + " wins!";
        }
        return this.name + ": " + results[0] + " wins\n" + opponent.getName() + ": " + results[1] + " wins\n" + returnMessage;
    }

    void dramaticFightToTheDeath(Hero opponent){
        String winningHero = "";
        senzuBean();
        opponent.senzuBean();
        while (hitPoints > 0 && opponent.hitPoints > 0){
            attack(opponent);
            System.out.println(name + ": " + hitPoints + "\t" + opponent.name + ": " + opponent.hitPoints);
        }
        if (hitPoints == 0){
            winningHero = opponent.getName();
        }
        else {
            winningHero = name;
        }
        System.out.println(winningHero + " wins!");
    }
}
