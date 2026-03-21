import type { Metadata } from "next";
import { Rubik } from "next/font/google";
import "./globals.css";

const rubik = Rubik({
  subsets: ["latin"],
  weight: ["300", "400", "500", "700", "900"],
  style: ["normal", "italic"],
  display: "swap",
  variable: "--font-rubik",
});

export const metadata: Metadata = {
  title: "G-Scores",
  description: "G-Scores",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="vi" suppressHydrationWarning>
      <body className={`${rubik.className} antialiased`}>{children}</body>
    </html>
  );
}
