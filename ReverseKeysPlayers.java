
public class ReverseKeysPlayers implements GamePowers {

    private PlayerComponent player1, player2;
    private char[] originalKeysPlayer1, originalKeysPlayer2;

    public ReverseKeysPlayers(PlayerComponent player1, PlayerComponent player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.originalKeysPlayer1 = player1.getKeys();
        this.originalKeysPlayer2 = player2.getKeys();
    }

    @Override
    public void activatePower() {
        player1.setKeys(new char[]{'s', 'w'});
        player2.setKeys(new char[]{'k', 'i'});
    }

    @Override
    public void deActivatePower() {
        player1.setKeys(originalKeysPlayer1);
        player2.setKeys(originalKeysPlayer2);
    }
}
