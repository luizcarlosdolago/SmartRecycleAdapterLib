package br.com.k2sistemas.library.annotations;


import android.support.annotation.IdRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Created by luizlago on 02/01/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface BindImageView {
    @IdRes int value();
}
