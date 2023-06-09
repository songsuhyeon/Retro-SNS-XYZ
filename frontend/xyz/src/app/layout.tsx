import "./globals.css";
import localFont from "next/font/local";
import Script from "next/script";
import ReactQueryProvider from "./ReactQueryProvider";
import { ReduxProviders } from "@/store/provider";
import AuthProvider from "./AuthProvider";

export const metadata = {
  title: "XYZ",
  description: "Generated by create next app",
};

const pixelFont = localFont({
  src: "../../public/font/NeoDunggeunmoPro-Regular.woff",
  display: "swap",
});

const KAKAO_SDK_URL = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.NEXT_PUBLIC_KAKAO_JS_KEY}&libraries=services,clusterer&autoload=false`;

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="ko" className={pixelFont.className}>
      <body>
        <Script src={KAKAO_SDK_URL} strategy="beforeInteractive" />
        <ReduxProviders>
          <ReactQueryProvider>
            <AuthProvider>
              <div className="px-5 my-[68px]">{children}</div>
            </AuthProvider>
          </ReactQueryProvider>
        </ReduxProviders>
      </body>
    </html>
  );
}
