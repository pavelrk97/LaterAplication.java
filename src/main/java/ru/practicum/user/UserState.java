package ru.practicum.user;

public enum UserState {
    ACTIVE, BLOCKED, DELETED
}
/* Доступно два вида сохранения. Первый — строковый вид EnumType.STRING. Он сохранит в базу строку, полученную
 в результате вызова метода enum-класса state.name(). Второй — целочисленный вид EnumType.ORDINAL. Он сохранит
 порядковый номер элемента перечисления — его также можно получить через вызов метода state.ordinal().
 */