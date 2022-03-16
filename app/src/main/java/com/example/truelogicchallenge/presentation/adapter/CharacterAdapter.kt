package com.example.truelogicchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.truelogicchallenge.databinding.ItemListBinding
import com.example.truelogicchallenge.domain.model.Character
import com.example.truelogicchallenge.presentation.model.PCharacter

class CharacterAdapter(
    private val onToggleClick: (state: Boolean, characterId: Int) -> Unit
): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characterList: List<PCharacter> = listOf()
    inner class CharacterViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    fun setList(list: List<PCharacter>) {
        this.characterList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val listItem: PCharacter = characterList[position]
        holder.binding.nameCharacter.text = listItem.name
        holder.binding.aliasCharacter.text = listItem.nickname

        Glide.with(holder.itemView.context)
            .load(listItem.image)
            .into(holder.binding.imageCharacter)

        holder.binding.heartButton.setOnClickListener {
            onToggleClick.invoke(
                holder.binding.heartButton.isChecked,
                characterList[position].charId
            )
        }

        holder.binding.heartButton.isChecked = characterList[position].isFavorite
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}