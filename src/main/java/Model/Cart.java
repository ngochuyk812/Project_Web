package Model;

public class Cart {
    private String username;
    private Post post;
    private int amount;

    public Cart() {
    }

    public Cart(String username, Post post, int amount) {
        this.username = username;
        this.post = post;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }
    public double total(){
        return this.getPost().getPrice() * this.getAmount();
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return username + "-" + post + "-" + amount ;
    }
}
