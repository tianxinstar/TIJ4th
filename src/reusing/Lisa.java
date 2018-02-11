package reusing;
//: reusing/Lisa.java
// {CompileTimeError} (Won't compile)

class Lisa extends Bart {
  @Override void doh(Milhouse m) {
    System.out.println("doh(Milhouse)");
  }
} ///:~
