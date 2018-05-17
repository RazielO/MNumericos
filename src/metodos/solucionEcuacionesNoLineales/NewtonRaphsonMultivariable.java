package metodos.solucionEcuacionesNoLineales;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.objecthunter.exp4j.Expression;

import java.util.HashMap;
import java.util.Map;

public class NewtonRaphsonMultivariable
{
    Expression f, g, Fx, Fy, Gx, Gy;
    double evalF, evalG, evalFx, evalFy, evalGx, evalGy, x, y, deltaX, deltaY, errorX, errorY, x1, y1, ep;

    public NewtonRaphsonMultivariable(Expression f, Expression g, Expression fx, Expression fy, Expression gx, Expression gy, double x, double y, double ep)
    {
        this.f = f;
        this.g = g;
        Fx = fx;
        Fy = fy;
        Gx = gx;
        Gy = gy;
        this.x = x;
        this.y = y;
        this.ep = ep;
    }

    private double calcularError(double actual, double anterior)
    {
        return Math.abs((actual - anterior) / actual) * 100;
    }

    private double nuevoValor(double delta, double anterior)
    {
        return anterior + delta;
    }

    private void evaluar()
    {
        Map<String, Double> vars = new HashMap<>();
        vars.put("x", this.x);
        vars.put("y", this.y);

        evalF = f.setVariables(vars).evaluate();
        evalG = g.setVariables(vars).evaluate();
        evalFx = Fx.setVariables(vars).evaluate();
        evalGx = Gx.setVariables(vars).evaluate();
        evalFy = Fy.setVariables(vars).evaluate();
        evalGy = Gy.setVariables(vars).evaluate();
    }

    private void calcularDeltas()
    {
        deltaX = ((evalG * evalFy) - (evalF * evalGy)) / ((evalFx * evalGy) - (evalGx * evalFy));
        deltaY = ((evalF * evalGx) - (evalG * evalFx)) / ((evalFx * evalGy) - (evalGx * evalFy));
    }

    public ObservableList<models.solucionEcuacionesNoLineales.NewtonRaphsonMultivariable> algoritmo()
    {
        boolean flag = true;
        int iteracion = 1;
        ObservableList<models.solucionEcuacionesNoLineales.NewtonRaphsonMultivariable> list = FXCollections.observableArrayList();
        models.solucionEcuacionesNoLineales.NewtonRaphsonMultivariable n;

        while (flag)
        {
            evaluar();
            calcularDeltas();
            x1 = nuevoValor(deltaX, x);
            y1 = nuevoValor(deltaY, y);

            errorX = calcularError(x1, x);
            errorY = calcularError(y1, y);

            flag = ((errorX > ep) && (errorY > ep));

            n = new models.solucionEcuacionesNoLineales.NewtonRaphsonMultivariable(iteracion,
                    String.format("%.6f", x), String.format("%.6f", y), String.format("%.6f", evalF),
                    String.format("%.6f", evalG), String.format("%.6f", evalFx), String.format("%.6f", evalFy),
                    String.format("%.6f", evalGx), String.format("%.6f", evalGy), String.format("%.6f", deltaX),
                    String.format("%.6f", deltaY), String.format("%.6f", errorX), String.format("%.6f", errorY));

            list.add(n);

            iteracion = iteracion + 1;
            y = y1;
            x = x1;
        }

        return list;
    }
}
