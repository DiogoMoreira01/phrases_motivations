package com.example.motivation.data

import com.example.motivation.infra.MotivationConstants
import kotlin.random.Random


data class Phrase(val description: String, val categoryID: Int)
class Mock {

    private val all = MotivationConstants.Filter.FILTER_IMAGE_ALL_INCLUSIVE
    private val happy = MotivationConstants.Filter.FILTER_IMAGE_HAPPY
    private val sunny = MotivationConstants.Filter.FILTER_IMAGE_SUNNY

    private val mListPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", happy),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny),
        Phrase("Você perde todas as chances que você não aproveita.", sunny),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sunny),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sunny),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sunny),
        Phrase("Se você acredita, faz toda a diferença.", sunny),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sunny)
    )

    fun getPhrases(value: Int): String {
        val filtered = mListPhrases.filter { it.categoryID ==value || value == all }

        return filtered[Random.nextInt(filtered.size)].description


    }
}