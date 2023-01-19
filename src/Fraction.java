import java.lang.Math;

public class Fraction {
    public int numerator;
    public int denominator;

    Fraction(int num, int denum){
        numerator = num;
        denominator = denum;
    }

    public static int gcd(int a, int b){
        return (a == 0) ? b : gcd(b%a, a);
    }

    public static Fraction plus(Fraction a, Fraction b){
        // a/b + c/d = (a*d + c*b)/(b*d)
        int num = a.numerator * b.denominator + b.numerator * a.denominator;
        int denum = a.denominator * b.denominator;
        Fraction c = new Fraction(num, denum);
        simplify(c);
        return c;
    }

    public static Fraction minus(Fraction a, Fraction b){
        // a/b - c/d = (a*d - c*b)/(b*d)
        int num = a.numerator * b.denominator - b.numerator * a.denominator;
        int denum = a.denominator * b.denominator;
        Fraction c = new Fraction(num, denum);
        simplify(c);
        return c;
    }
    
    public static Fraction multiply(Fraction a, Fraction b){
        // a/b * c/d = (a*c)/(b*d)
        int num = a.numerator * b.numerator;
        int denum = a.denominator * b.denominator;
        Fraction c = new Fraction(num, denum);
        simplify(c);
        return c;
    }

    public static Fraction divide(Fraction a, Fraction b){
        // (a/b) / (c/d) = (a/b) * (d/c)
        int num = a.numerator * b.denominator;
        int denum = a.denominator * b.numerator;
        Fraction c = new Fraction(num, denum);
        simplify(c);
        return c;
    }

    private static void simplify(Fraction a){
        int num = a.numerator;
        int denum = a.denominator;
        int g = gcd(Math.abs(num), Math.abs(denum));
        num /= g;
        denum /= g;
        a.numerator = num;
        a.denominator = denum;
    }

    public static void display(Fraction a){
        System.out.printf("%d/%d\n", a.numerator, a.denominator);
    }

    public static void main(String args[]){
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(3, 5);
        Fraction c = Fraction.plus(a, b);
        display(c);
        Fraction d = Fraction.plus(c, c);
        display(d);
    }
}
