package tk.mengxin.pdfimageview;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class MainActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    private ImageLoader mImageLoader;
    private static final String TAG = "junk";
    RelativeLayout pdfImageContainer;
//    @Bind(R.id.tab1)
//    ImageView mTab1;
//    @Bind(R.id.tab2)
//    ImageView mTab2;
//    @Bind(R.id.tab3)
//    ImageView mTab3;
//    @Bind(R.id.tab4)
//    ImageView mTab4;
//    @Bind(R.id.tab5)
//    ImageView mTab5;

//    @Bind(R.id.pdf_image_container)
//    RelativeLayout pdfImageContainer;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View rootView = super.onCreateView(name, context, attrs);
        //utterKnife.bind(this);
        return rootView;
    }

    /**
     * We will implement a feature of drag some tab image from the top fo the NetworkImage view,
     * Then put the tab image on the NetworkImage View at the position of drop.
     *
     * 1. ImageView copy or duplicate
     * 2. How to put a ImageView on other ImageView base on the position of drop.
     * 3.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mImageLoader = VolleySingleton.getInstance().getImageLoader();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        final NetworkImageView pdfImage = (NetworkImageView) findViewById(R.id.pdf_image);
        pdfImage.setImageUrl("https://dl.dropboxusercontent.com/u/109249235/pdf.jpg",mImageLoader);

        pdfImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;

            }
        });

//        register a long click listener for the balls
        findViewById(R.id.tab1).setOnLongClickListener(this);
        findViewById(R.id.tab2).setOnLongClickListener(this);
        findViewById(R.id.tab3).setOnLongClickListener(this);
        findViewById(R.id.tab4).setOnLongClickListener(this);
        findViewById(R.id.tab5).setOnLongClickListener(this);

//        register drag event listeners for the target layout containers
        findViewById(R.id.tabs).setOnDragListener(this);
        pdfImageContainer = (RelativeLayout) findViewById(R.id.pdf_image_container);
        pdfImageContainer.setOnDragListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onDrag(View receivingLayoutView, DragEvent dragEvent) {
        View draggedImageView = (View) dragEvent.getLocalState();

        // Handles each of the expected events
        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                Log.i(TAG, "drag action started");

                // Determines if this View can accept the dragged data
                if (dragEvent.getClipDescription()
                        .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    Log.i(TAG, "Can accept this data");

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                } else {
                    Log.i(TAG, "Can not accept this data");

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.i(TAG, "drag action entered");
//                the drag point has entered the bounding box
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                Log.i(TAG, "drag action location" + dragEvent.getX());
                Log.i(TAG, "drag action location" + dragEvent.getY());
                /*triggered after ACTION_DRAG_ENTERED
                stops after ACTION_DRAG_EXITED*/
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.i(TAG, "drag action exited");
//                the drag shadow has left the bounding box
                return true;

            case DragEvent.ACTION_DROP:
                  /* the listener receives this action type when
                  drag shadow released over the target view
            the action only sent here if ACTION_DRAG_STARTED returned true
            return true if successfully handled the drop else false*/
                ViewGroup draggedImageViewParentLayout
                        = (ViewGroup) draggedImageView.getParent();
                ImageView imageView = new ImageView(this);
                RelativeLayout.LayoutParams layoutParams =
                        new RelativeLayout.LayoutParams(100,100);
                switch (draggedImageView.getId()) {
                    case R.id.tab1:
                        Log.i(TAG, "tab1");
                        //draggedImageViewParentLayout.removeView(draggedImageView);
                        //LinearLayout bottomLinearLayout = (LinearLayout) receivingLayoutView;
                        //dragEvent.getX()
                        //bottomLinearLayout.addView(draggedImageView);
                        imageView = new ImageView(this);
                        imageView.setBackground(getResources().getDrawable(R.drawable.ic_person_pin_black_24dp));
                        Log.i(TAG, "x: " + String.valueOf(dragEvent.getX()) + "  Y: " + String.valueOf(dragEvent.getY()));
                        pdfImageContainer.addView(imageView,layoutParams);
                        //draggedImageView.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.tab2:
                        Log.i(TAG, "tab2");
                        //draggedImageViewParentLayout.removeView(draggedImageView);
                        //LinearLayout bottomLinearLayout = (LinearLayout) receivingLayoutView;
                        //dragEvent.getX()
                        //bottomLinearLayout.addView(draggedImageView);
                        imageView = new ImageView(this);
                        imageView.setBackground(getResources().getDrawable(R.drawable.ic_format_shapes_black_24dp));
                        layoutParams.setMargins((int)dragEvent.getX(),(int)dragEvent.getY(),0,0);
                        Log.i(TAG, "x: " + String.valueOf(dragEvent.getX()) + "  Y: " + String.valueOf(dragEvent.getY()));
                        pdfImageContainer.addView(imageView,layoutParams);
                        //draggedImageView.setVisibility(View.VISIBLE);
                        return true;
                    default:
                        Log.i(TAG, "in default");
                        //draggedImageViewParentLayout.removeView(draggedImageView);
                        //LinearLayout bottomLinearLayout = (LinearLayout) receivingLayoutView;
                        //dragEvent.getX()
                        //bottomLinearLayout.addView(draggedImageView);
                        imageView = new ImageView(this);
                        imageView.setBackground(getResources().getDrawable(R.drawable.ic_person_pin_black_24dp));

                        layoutParams.setMargins((int)dragEvent.getX(),(int)dragEvent.getY(),0,0);
                        Log.i(TAG, "x: " + String.valueOf(dragEvent.getX()) + "  Y: " + String.valueOf(dragEvent.getY()));
                        pdfImageContainer.addView(imageView,layoutParams);
                        //draggedImageView.setVisibility(View.VISIBLE);
                        return true;
                }

            case DragEvent.ACTION_DRAG_ENDED:

                Log.i(TAG, "drag action ended");
                Log.i(TAG, "getResult: " + dragEvent.getResult());

//                if the drop was not successful, set the ball to visible
                if (!dragEvent.getResult()) {
                    Log.i(TAG, "setting visible");
                    draggedImageView.setVisibility(View.VISIBLE);
                }

                return true;
            // An unknown action type was received.
            default:
                Log.i(TAG, "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }

    @Override
    public boolean onLongClick(View imageView) {
        //        the ball has been touched
//            create clip data holding data of the type MIMETYPE_TEXT_PLAIN
        ClipData clipData = ClipData.newPlainText("", "");

        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageView);
            /*start the drag - contains the data to be dragged,
            metadata for this data and callback for drawing shadow*/
        imageView.startDrag(clipData, shadowBuilder, imageView, 0);
//        we're dragging the shadow so make the view invisible
        //imageView.setVisibility(View.INVISIBLE);
        return true;
    }
}
