import "./globals.css";
import Providers from "@/app/provider/providers";

export const metadata = {
  title: "Chat-Bot Web Socket Microservice",
  description: "Sistema de Chat em Tempo Real",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="pt-BR">
      <body>
        <Providers>{children}</Providers>
      </body>
    </html>
  );
}
