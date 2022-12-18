public class Node {

    private Integer coefficient;
    private Integer exponent;
    private Node next;


    public Node(Integer coefficient, Integer exponent, Node next) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = next;
    }

    @Override
    public String toString()
    {
        return coefficient + "x^" + exponent;
    }

    public Node(Node node){
        this.coefficient = node.getCoefficient();
        this.exponent = node.getExponent();
        this.next = node.getNext();
    }



    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getExponent() {
        return exponent;
    }

    public void setExponent(Integer exponent) {
        this.exponent = exponent;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
