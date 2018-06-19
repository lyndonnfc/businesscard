package com.nfc.lyndon.businesscard.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.base.BaseFragment;
import com.nfc.lyndon.businesscard.contract.CardListContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.CardModel;
import com.nfc.lyndon.businesscard.presenter.CardListPresenter;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.ui.adapter.CardAdapter;
import com.nfc.lyndon.businesscard.util.AppUtils;
import com.nfc.lyndon.businesscard.util.BitmapUtils;
import com.nfc.lyndon.businesscard.util.ScreenUtils;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.widget.PictureSelectorDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 名片列表
 */
@RuntimePermissions
public class CardListFragment extends BaseFragment<CardListPresenter, CardModel> implements
        BaseQuickAdapter.OnItemClickListener, CardListContract.CardListView,
        PictureSelectorDialog.OnDialogClickListener, TextView.OnEditorActionListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.iv_input)
    ImageView ivInput;
    @BindView(R.id.lay_add_card)
    ConstraintLayout layAddCard;
    @BindView(R.id.iv_flow_input)
    ImageView ivFlowInput;
    @BindView(R.id.iv_flow_camera)
    ImageView ivFlowCamera;
    Unbinder unbinder;

    private String keyword = "";

    public CardAdapter mAdapter;

    private String path;

    private Bitmap bitmap;

    private PictureSelectorDialog dialog;

    @Override
    protected int getContentId() {
        return R.layout.fragment_card_list;
    }

    @Override
    protected void initView() {
        layAddCard.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvList.setLayoutManager(linearLayoutManager);
        initAdapter();
        mAdapter.setOnItemClickListener(this);
        rvList.setAdapter(mAdapter);

        etSearch.setOnEditorActionListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.requestCardList(PreferenceManager.getInstance().getLong(PreferenceManager.UID), "");
    }

    public void initAdapter() {
        View headView = new View(mContext);
        headView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtils.dip2px(mContext, 20)));
        View footerView = new View(mContext);
        footerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtils.dip2px(mContext, 20)));
        mAdapter = new CardAdapter(R.layout.item_card, new ArrayList<CardEntity>());
        mAdapter.addHeaderView(headView);
        mAdapter.addFooterView(footerView);
    }

    @Override
    protected CardListPresenter initPresenter() {
        return new CardListPresenter(mContext);
    }

    @Override
    protected CardModel initModel() {
        return new CardModel();
    }

    @OnClick({R.id.iv_search, R.id.iv_camera, R.id.iv_input,
            R.id.iv_flow_input, R.id.iv_flow_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                keyword = etSearch.getText().toString().trim();
                mPresenter.requestCardList(PreferenceManager.getInstance().getLong(PreferenceManager.UID), keyword);
                break;
            case R.id.iv_input:
            case R.id.iv_flow_input:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isCreate", true);
                EditActivity.startActivity(mContext, bundle);
                break;
            case R.id.iv_flow_camera:
            case R.id.iv_camera:
                dialog = new PictureSelectorDialog(mContext, R.style.transparent_dialog);
                dialog.setOnDialogClickListener(this);
                dialog.show();
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(position);
    }

    @Override
    public void showLoading(String message) {
        showDialog(message);
    }

    @Override
    public void hidLoading() {
        hidDialog();
    }

    @Override
    public void showAddView() {
        layAddCard.setVisibility(View.VISIBLE);
        rvList.setVisibility(View.GONE);
        ivFlowInput.setVisibility(View.GONE);
        ivFlowCamera.setVisibility(View.GONE);
    }

    @Override
    public void updateView(List<CardEntity> data) {
        layAddCard.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);
        ivFlowCamera.setVisibility(View.VISIBLE);
        ivFlowInput.setVisibility(View.VISIBLE);
        mAdapter.setNewData(data);
    }

    @Override
    public void camera() {
        CardListFragmentPermissionsDispatcher.takePhotoWithPermissionCheck(this);
    }

    @Override
    public void gallery() {
        CardListFragmentPermissionsDispatcher.albumWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void takePhoto() {
        path = mContext.getExternalCacheDir() + File.separator + "card.png";
        AppUtils.openCameraPage(mContext, this, path);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void album() {
        path = mContext.getExternalCacheDir() + File.separator + "card.png";
        AppUtils.openAlbumPage(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constants.CAMERA_REQUEST_CODE:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            bitmap = BitmapUtils.compressImage(BitmapFactory.decodeFile(path), path);
                            mPresenter.uploadCardFile(new File(path));
                        }
                    }).start();
                    break;
                case Constants.ALBUM_REQUEST_CODE:
                    if (data != null && data.getData() != null) {
                        final Uri uri = data.getData();
                        final Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                BitmapUtils.compressImage(bitmap, path);
                                mPresenter.uploadCardFile(new File(path));
                            }
                        }).start();
                    }
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bitmap = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            keyword = etSearch.getText().toString().trim();
            if (TextUtils.isEmpty(keyword)) {
                ToastUtils.toastShort("请先输入要搜索的内容");
                return false;
            }
            mPresenter.requestCardList(PreferenceManager.getInstance()
                    .getLong(PreferenceManager.UID), keyword);
        }
        return false;
    }
}
