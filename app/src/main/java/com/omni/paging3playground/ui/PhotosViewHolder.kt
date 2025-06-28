/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omni.paging3playground.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.shimmer.ShimmerFrameLayout
import com.omni.paging3playground.R
import com.omni.paging3playground.ui.model.UnsplashPhoto

class PhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.desc)
    private val shimmerContainer: ShimmerFrameLayout =
        itemView.findViewById(R.id.shimmerContainer)

    fun bind(photo: UnsplashPhoto?) {
        if (photo == null) {
            shimmerContainer.startShimmer()
            name.text = itemView.context.getString(R.string.loading)
        } else {
            shimmerContainer.stopShimmer()
            shimmerContainer.setShimmer(null)
            name.text = photo.user
        }
    }

    companion object {
        fun create(parent: ViewGroup): PhotosViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_recipe, parent, false)
            return PhotosViewHolder(view)
        }
    }
}
