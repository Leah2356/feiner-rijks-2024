package feiner.rijks;

import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import feiner.rijks.json.ArtObject;
import feiner.rijks.json.ArtObjectCollection;
import feiner.rijks.json.RijksService;
import feiner.rijks.json.RijksServiceFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.concurrent.Executors;

import com.andrewoid.ApiKey;

public class RijksSearchFrame extends JFrame {
    private final RijksService service;
    private final JButton previous;
    private final JButton next;
    private final JTextField searchBar;
    private final JPanel imagePanel;
    private int pageNumber = 0;
    ApiKey apiKey = new ApiKey();
    String keyString = apiKey.get();

    public RijksSearchFrame() {
        setTitle("ImageFrame");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        final JPanel utilityPanel = new JPanel();
        imagePanel = new JPanel();

        next = new JButton("next");
        previous = new JButton("previous");
        searchBar = new JTextField(25);
        utilityPanel.add(previous);
        utilityPanel.add(searchBar);
        utilityPanel.add(next);
        add(utilityPanel, BorderLayout.NORTH);
        add(new JScrollPane(imagePanel), BorderLayout.CENTER);

        service = new RijksServiceFactory().getService();
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNext();
            }
        });
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPrevious();
            }
        });
        searchBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSearch();
            }
        });
    }

    private void getData(String query, int pageNumber) {
        Disposable disposable;
        if (!query.isEmpty()) {
            disposable = service.querySearch(keyString, pageNumber, query)
                    // tells Rx to request the data on a background Thread
                    .subscribeOn(Schedulers.io())
                    // tells Rx to handle the response on Swing's main Thread
                    .observeOn(SwingSchedulers.edt())
                    //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                    .subscribe(
                            (response) -> handleResponse(response),
                            Throwable::printStackTrace);
        } else {
            disposable = service.pageSearch(keyString, pageNumber)
                    .subscribeOn(Schedulers.io())
                    .observeOn(SwingSchedulers.edt())
                    .subscribe(
                            (response) -> handleResponse(response),
                            Throwable::printStackTrace);
        }
    }

    private void onNext() {
        pageNumber++;
        getData(searchBar.getText(), pageNumber);
    }

    private void onPrevious() {
        pageNumber--;
        if (pageNumber < 0) {
            pageNumber = 0;
        }
        getData(searchBar.getText(), pageNumber);
    }

    private void onSearch() {
        pageNumber = 0;
        String query = searchBar.getText();
        Disposable disposable = service.querySearch(keyString, pageNumber, query)
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(SwingSchedulers.edt())
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(
                        (response) -> handleResponse(response),
                        Throwable::printStackTrace);
        }

    private void handleResponse(ArtObjectCollection response) {
        imagePanel.removeAll();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                for (ArtObject artObject : response.artObjects) {
                    JLabel label = createImageLabel(artObject);
                    SwingUtilities.invokeLater(() -> imagePanel.add(label));
                }
            }   catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private JLabel createImageLabel(ArtObject artObject) throws Exception {
        URL url = new URL(artObject.webImage.url);
        Image image = ImageIO.read(url);
        int width = 250;
        int height = -1;
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage));
        label.setToolTipText(artObject.title + " by " + artObject.principalOrFirstMaker);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked:)");
                try {
                    new ImageFrame(artObject.title, artObject.principalOrFirstMaker,
                            artObject.webImage.url).setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return label;
    }

    public static void main(String[] args) {
        new RijksSearchFrame().setVisible(true);
    }

}

