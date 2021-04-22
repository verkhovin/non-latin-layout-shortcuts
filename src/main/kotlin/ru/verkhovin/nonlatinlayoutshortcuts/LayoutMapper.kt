package ru.verkhovin.nonlatinlayoutshortcuts

object LayoutMapper {
    private val cyrillicLayoutMap = mapOf(
        'й' to 'Q',
        'ц' to 'W',
        'у' to 'E',
        'к' to 'R',
        'е' to 'T',
        'н' to 'Y',
        'г' to 'U',
        'ш' to 'I',
        'щ' to 'O',
        'з' to 'P',
        'ф' to 'A',
        'ы' to 'S',
        'в' to 'D',
        'а' to 'F',
        'п' to 'G',
        'р' to 'H',
        'о' to 'J',
        'л' to 'K',
        'д' to 'L',
        'я' to 'Z',
        'ч' to 'X',
        'с' to 'C',
        'м' to 'V',
        'и' to 'B',
        'т' to 'N',
        'ь' to 'M',
    )

    fun getSameKeyFromLatinLayout(from: Char) = cyrillicLayoutMap[from]
}
