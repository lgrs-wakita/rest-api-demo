package com.example.restapidemo.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Public
 * 
 * ユーザー認証不要のメソッドにつけるアノテーション
 *
 * @author Daisuke Wakita
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Public {

}