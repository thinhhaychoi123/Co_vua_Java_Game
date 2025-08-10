package me.huu_thinh.chess.assets;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.ImageTranscoder;
public class ImageInput {

	
	private static BufferedImage[] piece; 
	private static String s = "Classic";
	private static String duoi_img = "svg";
	
	public ImageInput() {
		piece = new BufferedImage[12];
		this.loadImage();
	}
	
	public void loadImage(){
		try {
//			piece[0] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/bb.png"));
//			piece[1] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/bk.png"));
//			piece[2] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/bn.png"));
//			piece[3] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/bp.png"));
//			piece[4] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/bq.png"));
//			piece[5] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/br.png"));
//			piece[6] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/wb.png"));
//			piece[7] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/wk.png"));
//			piece[8] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/wn.png"));
//			piece[9] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/wp.png"));
//			piece[10] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/wq.png"));
//			piece[11] =  ImageIO.read(getClass().getResourceAsStream("/theme/piece/"+s+"/wr.png"));
			
			piece[0] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/bb."+duoi_img);
			piece[1] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/bk."+duoi_img);
			piece[2] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/bn."+duoi_img);
			piece[3] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/bp."+duoi_img);
			piece[4] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/bq."+duoi_img);
			piece[5] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/br."+duoi_img);
			piece[6] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/wb."+duoi_img);
			piece[7] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/wk."+duoi_img);
			piece[8] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/wn."+duoi_img);
			piece[9] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/wp."+duoi_img);
			piece[10] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/wq."+duoi_img);
			piece[11] =  svgToBufferedImageFromResource("/theme/piece/"+s+"/wr."+duoi_img);
			
		} catch(Exception ex) {
		ex.printStackTrace();
		}
	}

	public static BufferedImage getPieceImage(int from) {
		return piece[from];
	}
	public static BufferedImage resize(BufferedImage img, int width,
            int height) {
        BufferedImage resized = new BufferedImage(width, height,
                img.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(img, 0, 0, width, height, 0, 0, img.getWidth(),
                img.getHeight(), null);
        g.dispose();
        return resized;
    }
	
	 public BufferedImage svgToBufferedImageFromResource(String resourcePath) throws Exception {
	        

	        try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
	            if (in == null) {
	                throw new IllegalArgumentException("Can't find resource name: " + resourcePath);
	            }
	            TranscoderInput input = new TranscoderInput(in);
	            BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
	            transcoder.transcode(input, null);
	            return transcoder.getImage();
	        }
	    }
	 class BufferedImageTranscoder extends ImageTranscoder {
         private BufferedImage image;

         @Override
         public BufferedImage createImage(int w, int h) {
             return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
         }

         @Override
         public void writeImage(BufferedImage img, org.apache.batik.transcoder.TranscoderOutput out) {
             this.image = img;
         }

         public BufferedImage getImage() {
             return image;
         }
     }
}
