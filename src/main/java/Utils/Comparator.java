package Utils;

public class Comparator {

    public double tempToKelvin(String tempInCelsius){
        double stdKelvinScale = 273.15;
        String actTempInCelsius = tempInCelsius.replaceAll("[^\\d]","");
        double tempFromUi = Double.parseDouble(actTempInCelsius);
        double tempConvertedToKelvin = tempFromUi+stdKelvinScale;
        return tempConvertedToKelvin;
    }

    public double fahrenheitToKelvin(String Fahrenheit){
        double stdKelvinScale = 273.15;
        String actTempInFahrenheit = Fahrenheit.replaceAll("[^\\d]","");
        double tempFromUi = Double.parseDouble(actTempInFahrenheit);
        double tempConvertedToKelvin = (tempFromUi - 32.00)*5/9+stdKelvinScale;
        return tempConvertedToKelvin;
    }

    public static boolean betweenExclusive(double x, double min, double max)
    {
        return x>min && x<max;
    }
}
