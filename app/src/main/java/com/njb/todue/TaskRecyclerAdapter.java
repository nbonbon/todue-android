package com.njb.todue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<TaskInfo> mTasks;

    public TaskRecyclerAdapter(Context context, List<TaskInfo> tasks) {
        mContext = context;
        mTasks = tasks;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_task_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskInfo task = mTasks.get(position);
        holder.mTextTitle.setText(task.getTitle());
        holder.mTextDescription.setText(task.getDescription());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextTitle;
        public final TextView mTextDescription;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.task_title);
            mTextDescription = itemView.findViewById(R.id.task_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TaskActivity.class);
                    intent.putExtra(TaskActivity.TASK_POSITION, mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
