import edu.duke.*

public class main {
  public void runHello() {
    FileResource res = new FileResource("hello_unicode.txt");
    for (String line : res.lines()) {
      System.out.println(line);
    }
  }
}
