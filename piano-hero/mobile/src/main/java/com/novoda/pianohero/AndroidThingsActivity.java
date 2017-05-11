package com.novoda.pianohero;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.novoda.pianohero.hax.GpioProxy;
import com.novoda.pianohero.hax.RGBmatrixPanel;
import com.novoda.pianohero.hax.RGBmatrixPanel.Color;

public class AndroidThingsActivity extends AppCompatActivity implements GameMvp.View {

    //    private GameMvp.Presenter presenter;
//    private UartDevice bus;
//    private MidiManager midiManager;
    private Handler handler;
    private RGBmatrixPanel rgbMatrixPanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("!!!", "I'm running");

//        midiManager = (MidiManager) getSystemService(Context.MIDI_SERVICE);
//        PeripheralManagerService service = new PeripheralManagerService();
//        try {
//            bus = service.openUartDevice("UART0");
//        } catch (IOException e) {
//            throw new IllegalStateException("Cannot open bus to input peripheral.", e);
//        }

        HandlerThread thread = new HandlerThread("BackgroundThread");
        thread.start();
        handler = new Handler(thread.getLooper());

        rgbMatrixPanel = new RGBmatrixPanel(new GpioProxy());
        rgbMatrixPanel.clearDisplay();
        handler.post(hax);

        int yellow = android.graphics.Color.YELLOW;
        Color color = new Color();
        color.red = android.graphics.Color.red(yellow);
        color.blue = android.graphics.Color.blue(yellow);
        color.green = android.graphics.Color.green(yellow);
        rgbMatrixPanel.drawPixel(3, 3, color);
        rgbMatrixPanel.drawPixel(4, 3, color);
        rgbMatrixPanel.drawPixel(5, 3, color);
        rgbMatrixPanel.drawPixel(6, 3, color);
        rgbMatrixPanel.drawPixel(7, 3, color);
        rgbMatrixPanel.drawPixel(8, 3, color);
        rgbMatrixPanel.drawPixel(9, 3, color);

        rgbMatrixPanel.drawPixel(3, 3, color);
        rgbMatrixPanel.drawPixel(3, 4, color);
        rgbMatrixPanel.drawPixel(3, 5, color);
        rgbMatrixPanel.drawPixel(3, 6, color);
        rgbMatrixPanel.drawPixel(3, 7, color);
        rgbMatrixPanel.drawPixel(3, 8, color);
        rgbMatrixPanel.drawPixel(3, 9, color);

        rgbMatrixPanel.drawPixel(9, 3, color);
        rgbMatrixPanel.drawPixel(9, 4, color);
        rgbMatrixPanel.drawPixel(9, 5, color);
        rgbMatrixPanel.drawPixel(9, 6, color);
        rgbMatrixPanel.drawPixel(9, 7, color);
        rgbMatrixPanel.drawPixel(9, 8, color);
        rgbMatrixPanel.drawPixel(9, 9, color);

        rgbMatrixPanel.drawPixel(3, 9, color);
        rgbMatrixPanel.drawPixel(4, 9, color);
        rgbMatrixPanel.drawPixel(5, 9, color);
        rgbMatrixPanel.drawPixel(6, 9, color);
        rgbMatrixPanel.drawPixel(7, 9, color);
        rgbMatrixPanel.drawPixel(8, 9, color);
        rgbMatrixPanel.drawPixel(9, 9, color);


//        GameMvp.Model gameModel = new GameModel(new SongSequenceFactory(), new SimplePitchNotationFormatter());
//        presenter = new GamePresenter(gameModel, this);
//        presenter.onCreate();
    }

    private final Runnable hax = new Runnable() {
        @Override
        public void run() {
            rgbMatrixPanel.foo();
            handler.post(this);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

//        try {
//            bus.setBaudrate(31250);
//            bus.setDataSize(8);
//            bus.setParity(UartDevice.PARITY_NONE);
//            bus.setStopBits(1);
//        } catch (IOException e) {
//            throw new IllegalStateException("Cannot configure peripheral", e);
//        }
//
//        try {
//            bus.registerUartDeviceCallback(callback);
//            Log.d("!!!", "Awaiting data");
//        } catch (IOException e) {
//            throw new IllegalStateException("Cannot register for data from peripheral", e);
//        }
//
//        midiManager.registerDeviceCallback(deviceCallback, handler);
//
//        handler.post(mockInput);
    }
//
//    private final MidiManager.DeviceCallback deviceCallback = new MidiManager.DeviceCallback() {
//        @Override
//        public void onDeviceAdded(MidiDeviceInfo device) {
//            Bundle properties = device.getProperties();
//            String name = properties.getString(MidiDeviceInfo.PROPERTY_NAME);
//            Log.d("TUT", "onDeviceAdded: " + name);
//            midiManager.openDevice(
//                    device,
//                    new MidiManager.OnDeviceOpenedListener() {
//                        @Override
//                        public void onDeviceOpened(MidiDevice device) {
//                            MidiOutputPort port = device.openOutputPort(0);
//                            port.onConnect(new MidiReceiver() {
//                                @Override
//                                public void onSend(byte[] msg, int offset, int count, long timestamp) throws IOException {
//                                    Log.d("TUT", "wut" + msg);
//                                }
//                            });
//                        }
//                    },
//                    handler
//            );
//        }
//    };
//
//    private final UartDeviceCallback callback = new UartDeviceCallback() {
//        @Override
//        public boolean onUartDeviceDataAvailable(UartDevice uart) {
//            try {
//                Log.d("!!!", "got data!");
//                final int maxCount = 16;
//                byte[] buffer = new byte[maxCount];
//
//                int count;
//                while ((count = uart.read(buffer, buffer.length)) > 0) {
//                    Log.d("!!!", "Read " + count + " bytes from peripheral");
//                }
//                for (byte b : buffer) {
//                    Log.d("!!!", "byte " + b);
//                }
//                Log.d("!!!", "wtf string " + Arrays.asList(new String(buffer)));
//                Log.d("!!!", "wtf array " + Arrays.asList(buffer));
//                Log.d("!!!", "wtf raw " + buffer);
//
//            } catch (IOException e) {
//                throw new IllegalStateException("Fubar", e);
//            }
//            return true;
//        }
//    };
//
//    private final Runnable mockInput = new Runnable() {
//        @Override
//        public void run() {
//            Log.d("!!!", "X Fake input");
//
//            presenter.onNotesPlayed(new Note(new Random().nextInt(127)));
//            final int maxCount = 16;
//            byte[] buffer = new byte[maxCount];
//            int count;
//            try {
//                while ((count = bus.read(buffer, buffer.length)) > 0) {
//                    Log.d("!!!", "X Read " + count + " bytes from peripheral");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            for (byte b : buffer) {
//                Log.d("!!!", "byte " + b);
//            }
//            Log.d("!!!", "wtf " + Arrays.asList(buffer));
//
//            handler.postDelayed(mockInput, SECONDS.toMillis(3));
//        }
//    };

    @Override
    public void showRound(RoundViewModel viewModel) {
        Log.d("!!!", viewModel.getStatusMessage());
        for (String notation : viewModel) {
            Log.d("!!!", "Note: " + notation);
        }
    }

    @Override
    public void showGameComplete(GameOverViewModel viewModel) {
        Log.d("!!!", viewModel.getMessage());
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        bus.unregisterUartDeviceCallback(callback);
//        midiManager.unregisterDeviceCallback(deviceCallback);
//        handler.removeCallbacks(mockInput);
//    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(hax);
//        try {
//            bus.close();
//        } catch (IOException e) {
//            Log.e("!!!", "Cannot close peripheral bus, might encounter strange behaviour on next app start.");
//        }
        super.onDestroy();
    }
}
