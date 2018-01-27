package com.example.phart.readingchallange.dummy;

import android.content.Context;

import java.util.SortedMap;

/**
 * Created by PHART on 1/26/2018.
 */

public interface MPV_Main {

    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */
    interface RequiredViewOps {
        // View operations permitted to Presenter
        Context getAppContext();

        Context getActivityContext();

        void notifyItemInserted(int layoutPosition);

        void notifyItemRangeChanged(int positionStart, int itemCount);
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface ProvidedPresenterOps {
//        // Presenter operations permitted to View
//        void clickNewNote(EditText editText);
//        // setting up recycler adapter
//        int getNotesCount();
//        NotesViewHolder createViewHolder(ViewGroup parent, int viewType);
//        void bindViewHolder(NotesViewHolder holder, int position);
    }

    /**
     * Required Presenter methods available to Model.
     */
    interface RequiredPresenterOps {
        // Presenter operations permitted to Model
        Context getAppContext();

        Context getActivityContext();
    }

    /**
     * Operations offered to Model to communicate with Presenter
     * Handles all data business logic.
     */
    interface ProvidedModelOps {
        // Model operations permitted to Presenter
        SortedMap<Book, BookRequirements> getBookRequirements();

        boolean loadData();

        boolean persistData();
    }

}
