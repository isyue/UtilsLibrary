package xmqian.myutils.simple

import android.os.Bundle
import android.widget.FrameLayout
import com.coszero.utilslibrary.base.BaseActivity
import com.coszero.utilslibrary.utils.ToastUtils
import xmqian.myutils.R
import xmqian.myutils.simple.dummy.DummyContent

class FragmentActivity : BaseActivity(), ItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        ToastUtils.showLongMsg("测试一波加载")
    }

    override fun setWantShowContentView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_fragment)
    }

    override fun initView() {
        val frameLayout = findView<FrameLayout>(R.id.frame_layout) as FrameLayout
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ItemFragment()).commit();
    }

}