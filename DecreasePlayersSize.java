
public class DecreasePlayersSize implements GamePowers {

    PlayerComponent player1, player2;

    public DecreasePlayersSize(PlayerComponent player1, PlayerComponent player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void activatePower() {
        player1.setSIZE(player1.getSIZE() / 2);
        player2.setSIZE(player2.getSIZE() / 2);
    }

    @Override
    public void deActivatePower() {
        player1.setSIZE(player1.getSIZE() * 2);
        player2.setSIZE(player2.getSIZE() * 2);
    }
}
