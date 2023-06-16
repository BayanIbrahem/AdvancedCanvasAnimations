package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac

fun isGameEnded(list: List<Int>): Int {
    val row1 = list[0] == list[1] && list[1] == list[2] && list[2] != -1
    val row2 = list[3] == list[4] && list[4] == list[5] && list[5] != -1
    val row3 = list[6] == list[7] && list[7] == list[8] && list[8] != -1

    val col1 = list[0] == list[3] && list[3] == list[6] && list[6] != -1
    val col2 = list[1] == list[4] && list[4] == list[7] && list[7] != -1
    val col3 = list[2] == list[5] && list[5] == list[8] && list[8] != -1

    val diameter1 = list[0] == list[4] && list[4] == list[8] && list[8] != -1
    val diameter2 = list[2] == list[4] && list[4] == list[6] && list[6] != -1
    if (row1) return 0
    if (row2) return 1
    if (row3) return 2
    if (col1) return 3
    if (col2) return 4
    if (col3) return 5
    if (diameter1) return 6
    if (diameter2) return 7
    return -1
}
fun gameEndingLineFromEnder(ender: Int): List<Int> {
    val list = mutableListOf<Int>(-1, -1, -1)
    when (ender) {
        0 -> {
            list[0] = 0
            list[1] = 1
            list[2] = 2
        }
        1 -> {
            list[0] = 3
            list[1] = 4
            list[2] = 5
        }
        2 -> {
            list[0] = 6
            list[1] = 7
            list[2] = 8
        }
        3 -> {
            list[0] = 0
            list[1] = 3
            list[2] = 6
        }
        4 -> {
            list[0] = 1
            list[1] = 4
            list[2] = 7
        }
        5 -> {
            list[0] = 2
            list[1] = 5
            list[2] = 8
        }
        6 -> {
            list[0] = 0
            list[1] = 4
            list[2] = 8
        }
        7 -> {
            list[0] = 2
            list[1] = 4
            list[2] = 6
        }
    }
    return list.toList()
}