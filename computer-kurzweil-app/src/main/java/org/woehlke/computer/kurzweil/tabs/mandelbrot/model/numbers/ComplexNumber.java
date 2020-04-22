package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

import lombok.*;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.Mandelbrot;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 18.08.15.
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode
@AllArgsConstructor
public class ComplexNumber implements Mandelbrot {

    private final double real;
    private final double img;

    public ComplexNumber() {
        this.real = 0.0d;
        this.img = 0.0d;
    }

    public ComplexNumber(final ComplexNumber complexNumber) {
        this.real = complexNumber.real;
        this.img = complexNumber.img;
    }

    public ComplexNumber copy() {
        double newRealZ = this.real;
        double newImgZ = this.img;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    public ComplexNumber half() {
        double newRealZ = this.real / 2.0d;
        double newImgZ = this.img  / 2.0d;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    public ComplexNumber minus(ComplexNumber complexNumber){
        double newRealZ = this.real - complexNumber.real;
        double newImgZ = this.img - complexNumber.img;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    public ComplexNumber plus(ComplexNumber complexNumber){
        double newRealZ = this.real + complexNumber.real;
        double newImgZ = this.img + complexNumber.img;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    public ComplexNumber square(){
        double realZ=real;
        double imgZ=img;
        double newRealZ=realZ*realZ-imgZ*imgZ;
        double newImgZ=2*realZ*imgZ;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    public boolean isNotDivergent(){
        return (( real*real + img*img ) < DIVERGENCE_THRESHOLD);
    }

    private final static double DIVERGENCE_THRESHOLD = 4.0d;
}