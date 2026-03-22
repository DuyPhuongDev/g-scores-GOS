import type { Metadata } from "next";
import { Rubik } from "next/font/google";
import "./globals.css";
import { Sidebar, TopBar } from "@/components/layout/layout-components";

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
      <body className={`${rubik.className} antialiased`}>
        <div className="min-h-screen bg-surface">
          <TopBar />
          <Sidebar />
          {children}
        </div>
      </body>
    </html>
  );
}
